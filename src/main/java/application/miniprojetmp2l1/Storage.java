package application.miniprojetmp2l1;

import java.util.Arrays;
import java.util.List;

public class Storage {
	public static class Enseignant {
		public static String id;
	}

	public static class Classe {
		public static String id;
	}

	public static class Matiere {
		public static String id;
	}

	public static class Sceance {
		public static Integer id;
		public static List<String> days = Arrays.asList("Lundi","Mardi","Mercredi","Jeudi","Vendredi");
	}
}
