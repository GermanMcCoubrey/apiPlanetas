package com.meli.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.meli.models.PeriodoModel;

public interface PeriodoRepository extends JpaRepository<PeriodoModel, Long>, JpaSpecificationExecutor<PeriodoModel> {
	List<PeriodoModel> findByPeriodo(String periodo);
	PeriodoModel findByDia(long dia);
	PeriodoModel findByMaximo(boolean maximo);
}
