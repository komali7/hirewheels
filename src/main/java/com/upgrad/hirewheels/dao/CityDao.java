package com.upgrad.hirewheels.dao;

import com.upgrad.hirewheels.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface CityDao extends JpaRepository<City, Integer> {
}
