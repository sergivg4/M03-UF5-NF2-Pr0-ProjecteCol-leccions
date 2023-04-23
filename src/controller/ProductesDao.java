/**
 * 
 */
package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;

import model.Persistable;
import model.ProducteAbstract;

/**
 * @author Sergi Valenzuela García M03-UF4 10 mar 2023
 */
public class ProductesDao implements Persistable<ProducteAbstract>, Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SortedMap<Integer, ProducteAbstract> productes = new TreeMap<>(Comparator.naturalOrder());

	public void guardarFitxer() throws IOException {

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("res/productes.txt"))) {
			oos.writeObject(productes);
		}

	}

	public void obrirFitxer() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("res/productes.txt"))) {

			@SuppressWarnings("unchecked")
			TreeMap<Integer, ProducteAbstract> productes2 = (TreeMap<Integer, ProducteAbstract>) ois.readObject();

			productes.putAll(productes2);

		} catch (FileNotFoundException e) {
			System.out.println("ha petado, pero sigue vivo, aunque el archivo no existe!");
		} catch (Exception e) {
			System.out.println("ha petado, pero no se porque, cosas...!" + e.getMessage());
		}
	}

	@Override
	public void guardar(ProducteAbstract prod) {
		productes.put(prod.getId(), prod);
	}

	@Override
	public void eliminar(int id) {
		productes.remove(id);
	}

	@Override
	public ProducteAbstract buscar(int id) {
		return productes.get(id);
	}

	@Override
	public TreeMap<Integer, ProducteAbstract> getMap() {
		return (TreeMap<Integer, ProducteAbstract>) productes;
	}

}
