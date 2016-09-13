package reportes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.util.HashMap;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import controller.ControllerBoutique;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public abstract class Reporte implements IReporte {
	protected String rutaUser;
	protected String nombre;
	protected String tipo;

	public Reporte() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setCurrentDirectory(new File(System.getProperty("user.home") + System.getProperty("file.separator")+ "Desktop"));
		chooser.setDialogTitle("Ingrese la ruta en la que desea generar");
		int i = chooser.showOpenDialog(new JFrame());
		if (i == JFileChooser.APPROVE_OPTION) {
			rutaUser = chooser.getSelectedFile().getAbsolutePath();
		}
		if (i == JFileChooser.CANCEL_OPTION) {
			chooser.hide();
		}
	}

	public void generar(HashMap<String, Object> hashMap, String rutaDiseño) {

		JFrame f = new JFrame();
		final JDialog loading = new JDialog(f);
		JPanel p1 = new JPanel(new BorderLayout());
		JLabel j = new JLabel("Por favor espere mientras se genera "+tipo+"...");
		j.setSize(new Dimension(200,500));
		p1.add(j, BorderLayout.CENTER);
		loading.setUndecorated(true);
		loading.getContentPane().add(p1);
		loading.pack();
		loading.setLocationRelativeTo(f);
		loading.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		loading.setModal(true);
		Thread thWorker = new Thread(new Runnable() {			
			@Override
			public void run() {
				SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
					@Override
					protected String doInBackground() throws InterruptedException {
						try {
							System.out.println("Loading...");
							final JasperDesign jd = JRXmlLoader.load(rutaDiseño);
							System.out.println("loaded");
							System.out.println("Compiling...");
							final JasperReport report = JasperCompileManager.compileReport(jd);
							System.out.println("Compiled...");
							System.out.println("Filling...");
							JasperPrint jp = JasperFillManager.fillReport(report, hashMap, new JREmptyDataSource());
							System.out.println("Filled");
							System.out.println("Exporting...");
							JasperExportManager.exportReportToPdfFile(jp, rutaUser + nombre + ".pdf");
							System.out.println("Exported");
							JOptionPane.showMessageDialog(null, nombre.substring(1,nombre.length()));
							return "Reporte hecho";
						} catch (JRException e) {
							JOptionPane.showMessageDialog(null, "Falla al hacer "+tipo+": "+e.getCause());
							System.out.println(e.getMessage()+", "+e.getCause());
							return "Falla al hacer el reporte: ";
						}
					}

					@Override
					protected void done() {
						loading.dispose();
					}
				};
				worker.execute();
				loading.setVisible(true);
				try {
					worker.get();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		thWorker.start();
		loading.dispose();
		f.dispose();
		thWorker.interrupt();
	}
}
