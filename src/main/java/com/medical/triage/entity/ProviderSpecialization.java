package com.medical.triage.entity;

import java.util.List;

public class ProviderSpecialization {
    private List<String> specializationList;

    public ProviderSpecialization() {
    }

    public List<String> getSpecializationList() {
        return specializationList;
    }

    public void setSpecializationList(List<String> specializationList) {
        this.specializationList = specializationList;
    }
}
