package com.example.Doctor.Controller;


import com.example.Doctor.Service.PatientService;
import com.example.Doctor.dao.DoctorRepository;
import com.example.Doctor.model.Doctor;
import com.example.Doctor.model.Patient;
import jakarta.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PatientController {
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    PatientService patientService;
    @PostMapping(value = "/patient")
    public String savePatient(@RequestBody String patientresponse){
        JSONObject json=new JSONObject(patientresponse);
        Patient patient=setPatient(json);

        patientService.savePatient(patient);
        return "Patient saved";

    }

    private Patient setPatient(JSONObject json) {
        Patient patient=new Patient();
        patient.setPatientId(json.getInt("patientId"));
        patient.setPatientName(json.getString("patientName"));
        patient.setAge(json.getInt("age"));
        patient.setPhoneNumber(json.getString("phoneNumber"));
        patient.setDiseaseType(json.getString("diseaseType"));
        patient.setGender(json.getString("gender"));
        Timestamp currTime=new Timestamp(System.currentTimeMillis());
        patient.setAdmitDate(currTime);
        String doctorId=json.getString("doctorId");
        Doctor doctor=doctorRepository.findById(Integer.valueOf(doctorId)).get();
        patient.setDoctorId(doctor);
        return patient;
    }
    @GetMapping("/patient")
    public ResponseEntity getPatient(@Nullable @RequestParam String doctorId, @Nullable @RequestParam String patientId){
        JSONArray patientDetails=patientService.findPatient(patientId,doctorId);
        return new ResponseEntity(patientDetails.toString(), HttpStatus.OK);
    }
}
