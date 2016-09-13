package reportes;

import java.util.HashMap;

public interface IReporte {
	public HashMap<String, Object> hash();
	public void generar(HashMap<String, Object> hashMap, String rutaDiseño);
}
