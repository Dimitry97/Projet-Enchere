package fr.eni.encheres.bo;


import java.util.Date;
import java.util.List;

public class ArticleVendu {
	
	//////////ATTRIBUTS //////////
	
	private int noArticle;
	private String nomArticle;
	private String description;
	private Date dateDebutEncheres;
	private Date dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	private int etatVente;
	private int noRetrait;
	private Categorie categorie;
	private Retrait lieuRetrait;
	private Utilisateur vendeur;
	private List<Enchere> enchere;

	
	////////// CONSTRUCTEURS ////////// 
	
	/**
	 * Constructeur par défault ne prenant aucun parametre
	 */
	public ArticleVendu() {

	}
	
	/**
	 * Constructeur avec tous les parametres necessaire pour la creation d'un article
	 * @param noArticle
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param miseAPrix
	 * @param prixVente
	 * @param etatVente
	 * @param categorie
	 * @param lieuRetrait
	 * @param vendeur
	 * @param enchere
	 */
	public ArticleVendu(int noArticle, String nomArticle, String description, Date dateDebutEncheres,
			Date dateFinEncheres, int miseAPrix, int prixVente, int etatVente, Categorie categorie,
			Retrait lieuRetrait, Utilisateur vendeur, List<Enchere> enchere) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.categorie = categorie;
		this.lieuRetrait = lieuRetrait;
		this.vendeur = vendeur;
		this.enchere = enchere;
	}
	
	public ArticleVendu(int noArticle, String nomArticle, String description, Date dateDebutEncheres,
			Date dateFinEncheres, int miseAPrix, int etatVente, Categorie categorie,
			Retrait lieuRetrait, Utilisateur vendeur) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.etatVente = etatVente;
		this.categorie = categorie;
		this.lieuRetrait = lieuRetrait;
		this.vendeur = vendeur;
	}
	
	
	
	
	/**
	 * Constructeur avec tous les parametres OBLIGATOIRE pour la creation d'un Article
	 * @param noArticle
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param etatVente
	 * @param categorie
	 * @param lieuRetrait
	 * @param vendeur
	 * @param enchere
	 */
	public ArticleVendu(int noArticle, String nomArticle, String description, Date dateDebutEncheres,
			Date dateFinEncheres, int etatVente, Categorie categorie, Retrait lieuRetrait, Utilisateur vendeur,
			List<Enchere> enchere) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.etatVente = etatVente;
		this.categorie = categorie;
		this.lieuRetrait = lieuRetrait;
		this.vendeur = vendeur;
		this.enchere = enchere;
	}

	
	/**
	 *------>test PB SQL
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param miseAPrix
	 * @param prixVente
	 * @param noRetrait
	 * @param categorie
	 * @param vendeur
	 */

	public ArticleVendu(String nomArticle, String description, Date dateDebutEncheres, Date dateFinEncheres,
			int miseAPrix, int prixVente, int noRetrait, Categorie categorie, Utilisateur vendeur) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.noRetrait = noRetrait;
		this.categorie = categorie;
		this.vendeur = vendeur;
	}


	////////// GETTERS AND SETTERS ////////// 

	public int getNoRetrait() {
		return noRetrait;
	}

	public void setNoRetrait(int noRetrait) {
		this.noRetrait = noRetrait;
	}
	
	
	
	/**
	 * Methode permettant de récupérer le nom d'un article vendu
	 * @return le nom de l'article (String)
	 */
	public String getNomArticle() {
		return nomArticle;
	}

	

	/**
	 * Methode permettant de modifier le nom d'un article vendu
	 * @param nomArticle (String)
	 */
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	/**
	 * Methode permettant de récupérer la description d'un article vendu
	 * @return la description de l'article (String)
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Methode permettant de modifier la description d'un article vendu
	 * @param description (string)
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Methode permettant de récupérer date de mise en vente d'un article
	 * @return la date du début de l'enchere (Date)
	 */
	public Date getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	/**
	 * Methode permettant de d'attribué une date de début des enchères sur un article
	 * @param dateDebutEncheres (Date)
	 */
	public void setDateDebutEncheres(Date dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	/**
	 * Methode permettant de récupérer la date fin des enchères sur un article
	 * @return la date de fin de l'enchere (Date)
	 */
	public Date getDateFinEncheres() {
		return dateFinEncheres;
	}

	/**
	 *  Methode permettant de d'attribué une date de fin des enchères sur un article
	 * @param dateFinEncheres (Date)
	 */
	public void setDateFinEncheres(Date dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	/**
	 * Methode permettant de récupérer le prix de départ d'un article
	 * @return
	 */
	public int getMiseAPrix() {
		return miseAPrix;
	}

	/**
	 * TODO : methode utile ?
	 * @param miseAPrix
	 */
	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	/**
	 * Methode permettant de récupérer le prix de vente final (a la fin des enchères) d'un article
	 * @return
	 */
	public int getPrixVente() {
		return prixVente;
	}

	/**
	 * TODO : Methode utile ?
	 * @param prixVente
	 */
	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	/**
	 * TODO : commentaire a faire
	 * @return
	 */
	public int getEtatVente() {
		return etatVente;
	}

	/**
	 * TODO : commentaire a faire
	 * @param etatVente
	 */
	public void setEtatVente(int etatVente) {
		this.etatVente = etatVente;
	}

	/**
	 * Methode permettant de récupérer la catégorie dans laquelle se trouve un article 
	 * @return 
	 */
	public Categorie getCategorie() {
		return categorie;
	}

	/**
	 * Methode permettant de récupérer le lieu de retrait de l'article vendu
	 * @return
	 */
	public Retrait getLieuRetrait() {
		return lieuRetrait;
	}


	/**
	 * Methode permettant de récupérer le pseudo du vendeur de l'article
	 * @return
	 */
	public Utilisateur getVendeur() {
		return vendeur;
	}


	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public void setLieuRetrait(Retrait lieuRetrait) {
		this.lieuRetrait = lieuRetrait;
	}

	public void setVendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}

	public void setEnchere(List<Enchere> enchere) {
		this.enchere = enchere;
	}

	/**
	 * Methode permettant de récupérer la liste des enchères ayant été fait sur l'article
	 * @return
	 */
	public List<Enchere> getEnchere() {
		return enchere;
	}

	/**
	 * Methode permettant de récupérer l'id de l'article 
	 * @return
	 */
	public int getNoArticle() {
		return noArticle;
	}
	

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ArticleVendu [noArticle=");
		builder.append(noArticle);
		builder.append(", nomArticle=");
		builder.append(nomArticle);
		builder.append(", description=");
		builder.append(description);
		builder.append(", dateDebutEncheres=");
		builder.append(dateDebutEncheres);
		builder.append(", dateFinEncheres=");
		builder.append(dateFinEncheres);
		builder.append(", miseAPrix=");
		builder.append(miseAPrix);
		builder.append(", prixVente=");
		builder.append(prixVente);
		builder.append(", etatVente=");
		builder.append(etatVente);
		builder.append(", categorie=");
		builder.append(categorie);
		builder.append(", lieuRetrait=");
		builder.append(lieuRetrait);
		builder.append(", vendeur=");
		builder.append(vendeur);
		builder.append(", enchere=");
		builder.append(enchere);
		builder.append("]");
		return builder.toString();
	}
	
	

}
