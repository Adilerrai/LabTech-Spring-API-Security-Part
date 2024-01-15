package com.developer.techlab;

import com.developer.techlab.entities.*;
import com.developer.techlab.entities.enums.*;
import com.developer.techlab.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class TechLabApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TechLabApplication.class, args);
	}

	@Autowired
	ReactifRepository reactifRepository;
	@Autowired
	UserLabRepository userLabRepository;
	@Autowired
	PatientRepository patientRepository;
	@Autowired
	AnalyseDetailsRepository analyseDetailsRepository;
	@Autowired
	TesteDetailsRepository testeDetailsRepository;
	@Autowired
	EchantillonRepository echantillonRepository;
	@Autowired
	AnalyseRepository analyseRepository;
	@Autowired
	TesteRepository testeRepository;
	@Autowired
	TesteReactifRepository testeReactifRepository;
	@Autowired
	ResultatRepository resultatRepository;

	@Override
	public void run(String... args) throws Exception {

		// CREATE RAPPORT
		Rapport rapport = new Rapport();
		rapport.setType("analytique ");
		rapport.setPeriode(PeriodeRapport.MOIS);
		rapport.setDonnee("données");
		rapport.setGraphique("graphique");

		// CREATE REACTIF
		Reactif reactif1 = new Reactif();
		reactif1.setLibelle("reactif");
		reactif1.setQuantite(23);
		reactif1.setDate_exp(LocalDate.of(2027, 10, 26));
		reactif1.setFournisseur("hasna");
		reactifRepository.save(reactif1);

		// CREATE USER
		UserLab userLab = new UserLab();
		userLab.setNom("hasna");
		userLab.setEmail("hasna@gmail.com");
		userLab.setMot_passe("2002");
		userLab.setRole(Role.TECHNICIEN);
		userLabRepository.save(userLab);

		// CREATE PATIENT
		Patient patient = new Patient();
		patient.setNom("hasna");
		patient.setEmail("hasna@gmail.com");
		patient.setTele("06587987");
		patient.setSexe(Sexe.FEMININ);
		patientRepository.save(patient);

		// CREATE ANALYSEDETAILS
		AnalyseDetails analyseDetails = new AnalyseDetails();
		analyseDetails.setLibelle("numeration");
		analyseDetailsRepository.save(analyseDetails);

		// CREATE TESTEDETAILS
		TesteDetails testeDetail = new TesteDetails();
		testeDetail.setLibelle("homoglobine");
		testeDetail.setMin(12);
		testeDetail.setMax(16);
        testeDetail.setAnalyseDetails(analyseDetails);
		testeDetailsRepository.save(testeDetail);

		analyseDetails.getTesteDetails().add(testeDetail);

		// CREATE ECHANTILLON
		Echantillon echantillon = new Echantillon();
		echantillon.setPatient(patient);
		echantillon.setDate_prev(LocalDate.of(2027, 10, 26));
		echantillon.setStatut(StatutEchantillon.EN_COURS_ANALYSE);
		echantillonRepository.save(echantillon);

		// CREATE ANALYSE
		Analyse analyse = new Analyse();
		analyse.setLibelle(analyseDetails.getLibelle());
		analyse.setDate_debut(LocalDate.of(2024, 10, 26));
		analyse.setDate_fin(LocalDate.of(2024,01,01));
		analyse.setEchantillon(echantillon);
		analyse.setUserLab(userLab);
		analyseRepository.save(analyse);

        patient.getAnalyses().add(analyse);
        userLab.getAnalyses().add(analyse);

		// CREATE TESTE
		Teste teste = new Teste();
		teste.setLibelle(testeDetail.getLibelle());
		teste.setValeur(14);
		teste.setMin(testeDetail.getMin());
		teste.setMax(testeDetail.getMax());
        teste.setAnalyse(analyse);
		testeRepository.save(teste);

		analyse.getTestes().add(teste);

		// CREATE TESTEREACTIF
		TesteReactif testeReactif = new TesteReactif();
		testeReactif.setTeste(teste);
		testeReactif.setReactif(reactif1);
		testeReactif.setQuantite(3);
		testeReactifRepository.save(testeReactif);

        teste.getTesteReactifs().add(testeReactif);
        reactif1.getTesteReactifs().add(testeReactif);

		// CREATE RESULTAT
		Resultat resultat = new Resultat();
		resultat.setTeste(teste);
		if(teste.getMin()<teste.getValeur() && teste.getMax()>teste.getValeur()){
			resultat.setResultat(ResultatTeste.NORMAL);
            resultatRepository.save(resultat);
		}
		else{
			resultat.setResultat(ResultatTeste.ANORMAL);
            resultatRepository.save(resultat);
		}
    }
}
