package com.jsm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.beans.BeanUtils;

import com.jsm.domain.Cliente;
import com.jsm.domain.Endereco;
import com.jsm.dto.ClienteNewDTO;

public class TesteReflexao {
   private Object testeDTO(Object dto, Object entity) {
	    Method[] dtoMethods = dto.getClass().getMethods();
	    Method[] entityMethods = entity.getClass().getMethods();
	    for(Method mdto:dtoMethods) {
	    	for(Method em:entityMethods) {
	    		Method setter = null;
	    		if(em.getName().startsWith("set")) {
	    			setter = em;
	    			//System.out.println("em"+em);
	    		}
	    		System.out.println("Set == null? "+setter==null);
	    		if(hasEqualsName(mdto, em)) {
	    			if(isGetter(mdto)) {
	    				String methodName = mdto.getName();
	    				methodName = methodName.replaceAll("get", "set");
	    				
	    				System.out.println("Method Setter:"+methodName);
	    				
	    				try {
	    					Object value = mdto.invoke(dto);
	    					System.out.println("Value: "+value);
	    					//setter = entity.getClass().getMethod(methodName);
	    					if(setter !=null) {
	    						setter.invoke(entity, value);
	    					}
		    				
						} catch ( SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	    				
	    				System.out.println("DTO METHOD: "+mdto.getName());
			    		System.out.println("ENTITY METHOD"+em.getName());
	    			}
	    			
	    		}
	    		
	    		
	    	}
	    	
	    	
	    }
	    
	    return entity;
   }
   
   public static void main(String[] args) {
	   ClienteNewDTO  cdto= new ClienteNewDTO();
	   
	   cdto.setBairro("Almerinda Chaves");
	   cdto.setCep("14.365-268");
	   cdto.setCidadeId(1L);
	   cdto.setComplemento("Apartamento 26");
	   cdto.setCpfCnpj("365.365.365/0004-9");
	   cdto.setEmail("empresa@gmail.com");
	   cdto.setLogradouro("Avenida das Camélias");
	   cdto.setNome("Empresa LTDA");
	   cdto.setNumero("1800");
	   cdto.setTipo(1);
	   
	   
	   
	   
	   
	   Cliente cliente = new Cliente();
	   Endereco endereco = new Endereco();
	   
	   
	   BeanUtils.copyProperties(cdto, cliente,"id");
	   BeanUtils.copyProperties(cdto, endereco,"id");
	   
	   
	   System.out.println(cliente);
	   System.out.println(endereco);
   }
   
   private boolean hasEqualsName(Method m1,Method m2) {
	   return m1.getName().equals(m2.getName());
   }
   
   private boolean isGetter(Method method) {
	   boolean is= method.getName().startsWith("get") && method.getReturnType()!=void.class
			   && method.getParameterTypes().length == 0 && !method.getName().contains("class");
	   
	   return is;
   }
}
