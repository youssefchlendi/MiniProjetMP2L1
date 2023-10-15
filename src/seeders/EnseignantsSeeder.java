package seeders;

import dao.EnseignantDao;
import models.Enseignant;

public class EnseignantsSeeder extends Seeders {

	EnseignantDao dao = new EnseignantDao();

	@Override
	public void seed() {
		try {
			dao.add(new Enseignant("766-59-4173", "Hewe Rottger", "175-815-6247"));
			dao.add(new Enseignant("335-93-1965", "Clementia Dufton", "120-227-4188"));
			dao.add(new Enseignant("787-97-9669", "Deloria Sully", "189-551-7584"));
			dao.add(new Enseignant("123-45-6789", "John Doe", "555-555-5555"));
			dao.add(new Enseignant("987-65-4321", "Jane Smith", "777-777-7777"));
			dao.add(new Enseignant("456-78-9012", "Michael Johnson", "123-456-7890"));
			dao.add(new Enseignant("234-56-7890", "Sarah Brown", "987-654-3210"));
			dao.add(new Enseignant("890-12-3456", "David Williams", "111-222-3333"));
			dao.add(new Enseignant("567-89-0123", "Linda Davis", "444-333-2222"));
			dao.add(new Enseignant("111-22-3333", "Chris Wilson", "666-999-8888"));
			dao.add(new Enseignant("222-33-4444", "Jessica Lee", "999-888-7777"));
			dao.add(new Enseignant("444-55-6666", "Andrew White", "333-666-9999"));
			dao.add(new Enseignant("333-66-7777", "Emily Adams", "555-777-1111"));
			dao.add(new Enseignant("555-77-8888", "Robert Miller", "888-111-4444"));
			dao.add(new Enseignant("999-88-9999", "Mary Turner", "222-444-8888"));
			dao.add(new Enseignant("666-99-6666", "Daniel Hall", "444-111-7777"));
			dao.add(new Enseignant("888-11-8888", "Sophia Scott", "777-333-5555"));
			dao.add(new Enseignant("222-22-2222", "James King", "111-555-7777"));
			dao.add(new Enseignant("777-77-7777", "Olivia Martinez", "666-888-4444"));
			dao.add(new Enseignant("111-11-1111", "William Wright", "444-777-2222"));
			dao.add(new Enseignant("333-33-3333", "Emma Perez", "555-999-3333"));
			dao.add(new Enseignant("444-44-4444", "Joseph Lewis", "777-111-9999"));
			dao.add(new Enseignant("555-55-5555", "Ava Young", "999-444-8888"));
			dao.add(new Enseignant("999-99-9999", "Michael Harris", "111-777-5555"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
