package com.example.mainservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@Table(name = "access_data")
@AllArgsConstructor
@NoArgsConstructor
public class AccessData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private Boolean enabled;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccessData accessData = (AccessData) o;
        return Objects.equals(id, accessData.id) && Objects.equals(username, accessData.username) && Objects.equals(password, accessData.password) && Objects.equals(enabled, accessData.enabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, enabled);
    }
}
