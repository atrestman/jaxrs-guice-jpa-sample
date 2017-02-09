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
@Entity(name="order")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id = null;

    private Long petId = null;

    private Integer quantity = null;

    private Long shipDateTimestamp = null;

    private String status = null;

    private Boolean complete = false;

}
