package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

public enum Role {
     ROLE_ADMIN,
     ROLE_VENDOR,
     ROLE_USER
}