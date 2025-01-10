package main;

import java.awt.EventQueue;

import com.centrosanluis.tiendaBD.vista.ProductosView;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductosView frame = new ProductosView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
