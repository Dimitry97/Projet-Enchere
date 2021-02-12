package fr.eni.encheres.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.artcileVendu.ArticleVenduDAO;
import fr.eni.encheres.dal.artcileVendu.ArticleVenduImpl;
import fr.eni.encheres.dal.categorie.CategorieDAO;
import fr.eni.encheres.dal.categorie.CategorieImpl;
import fr.eni.encheres.dal.retrait.RetraitDAO;
import fr.eni.encheres.dal.retrait.RetraitImpl;
import fr.eni.encheres.methode.Methodes;

/**
 * Servlet implementation class NouvelleVenteServlet
 */
@WebServlet("/NouvelleVenteServlet")
public class NouvelleVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleVenduDAO nouvelVente;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NouvelleVenteServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// recuperation la session utlisateur
		HttpSession session = request.getSession();
		
		// recuperation des données de la session (données utilisateurs connecté)
		String nvRue = (String) session.getAttribute("rue");
		String nvCP = (String) session.getAttribute("codePostal");
		String nvVille = (String) session.getAttribute("ville");


		// ajoute les données récuperées à des variable utilisable dans le doPost
		session.setAttribute("rue", nvRue);
		session.setAttribute("codePostal", nvCP);
		session.setAttribute("ville", nvVille);

		
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/nouvelleVente2.jsp").forward( request, response );
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// récuperer les données pour ajouter un nouvel article à la vente (nvXXX = nouvelle vente XXX)
		String nvArticle = request.getParameter("article");
		String nvDescription = request.getParameter("description");
		//
		String nvCategorie = request.getParameter("categorie"); 
		//   ////String nvPhoto = request.getParameter("photo"); // attention, objet image
		int nvMiseAPrix = (Integer.parseInt(request.getParameter("miseAPrixArticle")));
		String dateDebut = request.getParameter("debutEnchere"); // attention, ne veut pas de LocalDate, propose String, à vérifier
		String dateFin = request.getParameter("finEnchere");
		String nvRue = request.getParameter("rue");
		String nvCP = request.getParameter("codePostal");
		String nvVille = request.getParameter("ville");
		
		// Gestion des dates de début et de fin
		Date nvDateDebut = null, nvDateFin = null;
		try {
			nvDateDebut = Methodes.dateJSPVersJava(dateDebut);
			nvDateFin = Methodes.dateJSPVersJava(dateFin);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		// Recherche catégorie grace au libelle puis recherche de la categorie grace au no 
		CategorieDAO noC = new CategorieImpl();
		Categorie noCategorie = new Categorie();

		try {
			noCategorie = noC.rechercheNoCategorie(nvCategorie);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Retrait nvRetrait = new Retrait();
		nvRetrait.setCodePostal(nvCP);
		nvRetrait.setRue(nvRue);
		nvRetrait.setVille(nvVille);
		
		RetraitDAO retraitDAO = new RetraitImpl();
		try {
			retraitDAO.ajoutRetrait(nvRetrait);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		ArticleVendu nvArticleAVendre = new ArticleVendu();
		nvArticleAVendre.setNomArticle(nvArticle);
		nvArticleAVendre.setDescription(nvDescription);
		nvArticleAVendre.setDateDebutEncheres(nvDateDebut);
		nvArticleAVendre.setDateFinEncheres(nvDateFin);
		nvArticleAVendre.setMiseAPrix(nvMiseAPrix);
		nvArticleAVendre.setPrixVente(nvMiseAPrix);
		nvArticleAVendre.setLieuRetrait(nvRetrait);
		nvArticleAVendre.setCategorie(noCategorie);

		HttpSession session = request.getSession();
		int nvNoUtilisateur = (int) session.getAttribute("noUtilisateur");
	
		nvArticleAVendre.getVendeur().setNoUtilisateur(nvNoUtilisateur);
		
		
		
		ArticleVenduDAO nouvelleVente = new ArticleVenduImpl();
		RetraitDAO retrait = new RetraitImpl();
		try {
			try {
				nouvelleVente.nouvelArticle(nvArticleAVendre);
				retrait.ajoutRetrait(nvRetrait);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Enchere enchere = new Enchere();
		enchere.setArticleVendu(nvArticleAVendre);
		enchere.setDateEnchere(nvArticleAVendre.getDateFinEncheres());
		
		
		
		
		
		
		
		
		request.getRequestDispatcher("/WEB-INF/jsp/nouvelleVente2.jsp").forward(request, response);
		
		
	}

}