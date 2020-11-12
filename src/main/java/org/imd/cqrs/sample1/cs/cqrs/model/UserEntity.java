package org.imd.cqrs.sample1.cs.cqrs.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(indexes = {
        @Index(unique = true, columnList = "firstname ASC, lastname ASC")
})
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", initialValue = 1000, allocationSize = 1)
    @NonNull
    private String userid;

    @NonNull
    private String firstname;

    @NonNull
    private String lastname;
}
