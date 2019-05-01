package com.pulbet.chechapp;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.pvv.pulbet.model.Apuesta;
import com.pvv.pulbet.service.ApuestaService;
import com.pvv.pulbet.service.impl.ApuestaServiceImpl;

public class Checker implements Job{

	private ApuestaService apuestaService = null;
	
	public Checker() {
		apuestaService = new ApuestaServiceImpl();
	}
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		try {

			List<Apuesta> apuestas = apuestaService.findAll();
			for(Apuesta a : apuestas) {
				if(a.getProcesado() == 0) {
					System.out.println("Comprobando apuesta "+a.getIdApuesta());
					apuestaService.comprobarApuesta(a);
				}
			}
						
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
	}

}
