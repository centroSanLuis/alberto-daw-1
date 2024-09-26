import javax.swing.*;


public class Trab1_2 {
	public static void main(String[] args) {
		/* prog 2
		* pedir por teclado el tipo de cliente (normal, socio, vip)
		* pedir por teclado el importe de la compra
		* mostrar el total a pagar
		* sabiendo que normal no se aplica decuento, socio 5% y vip 20%*/

   
		String rango = JOptionPane.showInputDialog("Introduce tu rango (normal, socio, vip)");
		int pago = Integer.parseInt(JOptionPane.showInputDialog("Introduce el pago de la compra"));

		if (rango.equalsIgnoreCase("vip")) {
		    JOptionPane.showMessageDialog(null, "Tu rango es VIP, se aplica un descuento del 20%");
		    pago = pago - (pago * 20 / 100);
		} else if (rango.equalsIgnoreCase("socio")) {
		    JOptionPane.showMessageDialog(null, "Tu rango es Socio, se aplica un descuento del 5%");
		    pago = pago - (pago * 5 / 100);
		} else {
		    JOptionPane.showMessageDialog(null, "Tu rango es Normal, no se aplica ningún descuento");
		}

		JOptionPane.showMessageDialog(null, "El pago final  es: " + pago + "€");
    
	
	}



		
		
}








