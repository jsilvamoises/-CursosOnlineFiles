package com.jsm.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.jsm.domain.PagamentoComBoleto;

@Service
public class BoletoService {
	public void preencherPagamentoComBoleto(PagamentoComBoleto pgto, Date dataDoPedido) {
         Calendar cal = Calendar.getInstance();
         cal.setTime(dataDoPedido);
         cal.add(Calendar.DAY_OF_MONTH, 7);
         pgto.setDataVencimento(cal.getTime());
	}
}
