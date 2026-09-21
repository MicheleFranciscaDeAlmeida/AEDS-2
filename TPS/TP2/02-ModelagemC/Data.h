#ifndef DATA_H
#define DATA_H
	
	// Representa uma data com dia, mes e ano.
	typedef struct {
		int dia;
		int mes;
		int ano;
	} Data;

	// Converte uma string no formato AAAA-MM-DD para uma estrutura Data.
	Data parseData(char* s);

	// Formata uma estrutura Data no formato DD/MM/AAAA.
	void formatData(Data d, char* buffer);

#endif
