package com.example.bobbyranjan.ybsandroid.models;

import java.util.HashMap;

/**
 * Created by hari on 24/9/16.
 */

public class PatientMedicalHistory extends Model {
    String patientId;
    String date;
    String rt_rw;
    String g;
    String p;
    String a;
    String ah;
    String observations;
    String uk;
    String varices;
    String odema;
    String bb_tb;
    String td;
    String lila;
    String visit;
    String sf;
    String hpht;
    String tp;
    String complaints;
    String information;

    public PatientMedicalHistory(){
        pathPrefix="/patienthistory/";
        String[] keys = {"patientId","id"};
        pathKeys = keys;
    }

    public PatientMedicalHistory(String patientId, String date, String rt_rw, String g, String p, String a, String ah, String observations, String uk, String varices, String odema, String bb_tb, String td, String lila, String visit, String sf, String hpht, String tp, String complaints, String information) {
        pathPrefix="/patienthistory/";
        String[] keys = {"patientId","id"};
        pathKeys = keys;
        this.patientId = patientId;
        this.date = date;
        this.rt_rw = rt_rw;
        this.g = g;
        this.p = p;
        this.a = a;
        this.ah = ah;
        this.observations = observations;
        this.uk = uk;
        this.varices = varices;
        this.odema = odema;
        this.bb_tb = bb_tb;
        this.td = td;
        this.lila = lila;
        this.visit = visit;
        this.sf = sf;
        this.hpht = hpht;
        this.tp = tp;
        this.complaints = complaints;
        this.information = information;
    }

    @Override
    public HashMap<String, String> toMap() {
        HashMap<String, String> map = super.toMap();
        map.put("patientId",patientId);
        map.put("date",date);
        map.put("rt_rw", rt_rw);
        map.put("g", g);
        map.put("p", p);
        map.put("a", a);
        map.put("ah", ah);
        map.put("observations", observations);
        map.put("uk", uk);
        map.put("varices", varices);
        map.put("odema", odema);
        map.put("bb_tb", bb_tb);
        map.put("td", td);
        map.put("lila", lila);
        map.put("visit", visit);
        map.put("sf", sf);
        map.put("hpht", hpht);
        map.put("tp", tp);
        map.put("complaints", complaints);
        map.put("information", information);
        return map;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRt_rw() {
        return rt_rw;
    }

    public void setRt_rw(String rt_rw) {
        this.rt_rw = rt_rw;
    }

    public String getG() {
        return g;
    }

    public void setG(String g) {
        this.g = g;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getAh() {
        return ah;
    }

    public void setAh(String ah) {
        this.ah = ah;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getUk() {
        return uk;
    }

    public void setUk(String uk) {
        this.uk = uk;
    }

    public String getVarices() {
        return varices;
    }

    public void setVarices(String varices) {
        this.varices = varices;
    }

    public String getOdema() {
        return odema;
    }

    public void setOdema(String odema) {
        this.odema = odema;
    }

    public String getBb_tb() {
        return bb_tb;
    }

    public void setBb_tb(String bb_tb) {
        this.bb_tb = bb_tb;
    }

    public String getTd() {
        return td;
    }

    public void setTd(String td) {
        this.td = td;
    }

    public String getLila() {
        return lila;
    }

    public void setLila(String lila) {
        this.lila = lila;
    }

    public String getVisit() {
        return visit;
    }

    public void setVisit(String visit) {
        this.visit = visit;
    }

    public String getSf() {
        return sf;
    }

    public void setSf(String sf) {
        this.sf = sf;
    }

    public String getHpht() {
        return hpht;
    }

    public void setHpht(String hpht) {
        this.hpht = hpht;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public String getComplaints() {
        return complaints;
    }

    public void setComplaints(String complaints) {
        this.complaints = complaints;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
