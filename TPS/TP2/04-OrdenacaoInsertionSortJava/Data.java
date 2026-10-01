// Representa uma data com dia, mes e ano
public class Data {
private int dia;
private int mes;
private int ano;

// Construtor da classe Data
public Data(int dia, int mes, int ano) {
	this.dia = dia;
	this.mes = mes;
	this.ano = ano;
}

// Método parseData para converter uma data no formato AAAA-MM-DD
// para um objeto Data
public static Data parseData(String s) {
	if(s == null || s.isEmpty()) return new Data(0,0,0);
	// Separa a data no formato AAAA-MM-DD em suas partes
	String[] partes = s.split("-");
	if (partes.length == 3) {
		try {
			return new Data(
					Integer.parseInt(partes[2].trim()),
					Integer.parseInt(partes[1].trim()),
					Integer.parseInt(partes[0].trim()));
		} catch (Exception e) {
			return new Data(0,0,0);
		}

	}
	return new Data(0,0,0);
}

// Método format
public String format() {
	return String.format("%02d/%02d/%04d", dia, mes, ano);
}

public int getDia() {
	return dia;
}

public void setDia(int dia) {
	this.dia = dia;
}

public int getMes() {
	return mes;
}

public void setMes(int mes) {
	this.mes = mes;
}

public int getAno() {
	return ano;
}

public void setAno(int ano) {
	this.ano = ano;
}
}
