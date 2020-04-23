package com.novem.cours.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novem.cours.dao.CycleDao;
import com.novem.cours.entities.Cycle;

@Service
public class CycleService {

	@Autowired private CycleDao cycleDao;
	public Cycle creerCycle(Cycle cycle) {
		return cycleDao.save(cycle);
		
	}

	
}
