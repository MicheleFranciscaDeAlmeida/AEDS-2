#include "Data.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

Data parseData(char* s) {
	// Inicializa a data com zeros para o caso de a string ser invalida ou vazia.
	Data d = {0, 0, 0};

	if(s == NULL || strlen(s) == 0) {
		return d;
	}

	// Faz a leitura da data no formato AAAA-MM-DD e separa ano, mes e dia.
	int ano, mes, dia;
	if (sscanf(s, "%d-%d-%d", &ano, &mes, &dia) == 3) {
		d.dia = dia;
		d.mes = mes;
		d.ano = ano;
	}
	
	return d;
      }
     
     
     void formatData(Data d, char* buffer) {
	     // Converte a data para o formato DD/MM/AAAA.
	     sprintf(buffer, "%02d/%02d/%04d", d.dia, d.mes, d.ano);
     }


