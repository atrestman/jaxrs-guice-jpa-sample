package io.swagger.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by atrestman on 2/8/17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id = null;

    private String name = null;
}
