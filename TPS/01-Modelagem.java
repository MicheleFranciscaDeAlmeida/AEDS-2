public class Data {
	private int dia;
	private int mes;
	private int ano;

	public String format() {
	   return String.format("%02d/%02d/%04d", dia, mes, ano);
	}	
	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
     }
	public int getAno() {
		return ano;
     }   
        public void setAno(int ano) {
		this.ano = ano;
     }
 }
