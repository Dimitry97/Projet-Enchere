package fr.eni.encheres.dal.utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DBConnection;

public class UtilisateurImpl  implements UtilisateurDAO{
	
	
	private final static String RECHERCHER = "SELECT pseudo, nom, prenom, email, telephone, rue, code_postal, ville FROM UTILISATEURS WHERE pseudo = ?;";
	private final static String RECHERCHER_AVEC_CREDIT = "SELECT pseudo, nom, prenom, email, telephone, rue, code_postal, ville,mot_de_passe, credit, no_utilisateur FROM UTILISATEURS WHERE pseudo = ?;";
	private final static String INSERER = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) values (?,?,?,?,?,?,?,?,?,?,?);";
	private final static String SUPPRIMER = "DELETE FROM UTILISATEURS WHERE pseudo = ?;";
	private final static String MODIFIER = "UPDATE UTILISATEURS SET pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ? WHERE no_utilisateur = ?;";
			
	private final static String SELECTPSEUDOANDMDP = "SELECT pseudo, mot_de_passe FROM UTILISATEURS WHERE pseudo = ? AND mot_de_passe = ?";
	
	
	
	/**
	 * Methode pour selectionner un utilisateur avec tous les parametres
	 * @param pseudo
	 * @return un utilisateur
	 * @finally libere les connexions ouvertes
	 */
	@Override
	public Utilisateur rechercherProfilParPseudo(String pseudo) throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Utilisateur utilisateur = new Utilisateur();
		
		cnx = DBConnection.seConnecter();
		try {
			pstmt = cnx.prepareStatement(RECHERCHER);
			pstmt.setString(1, pseudo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("Prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCodePostal(rs.getString("code_postal"));
				utilisateur.setVille(rs.getString("ville"));
			}
		} catch (SQLException e) {
			throw new DALException("echec de recherche profil par pseudo");
		}finally {
			DBConnection.seDeconnecter(cnx, pstmt);
		}
				
		return utilisateur;
	}
	
	
	/**
	 * Methode pour selectionner un utilisateur avec tous les parametres
	 * @param pseudo
	 * @return un utilisateur
	 * @finally libere les connexions ouvertes
	 */
	@Override
	public Utilisateur rechercherProfilParPseudoAvecCredit(String pseudo) throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Utilisateur utilisateur = new Utilisateur();
		
		cnx = DBConnection.seConnecter();
		try {
			pstmt = cnx.prepareStatement(RECHERCHER_AVEC_CREDIT);
			pstmt.setString(1, pseudo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("Prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCodePostal(rs.getString("code_postal"));
				utilisateur.setVille(rs.getString("ville"));
				utilisateur.setCredit(rs.getInt("credit"));
				utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
			}
		} catch (SQLException e) {
			throw new DALException("echec de recherche profil par pseudo avec credit");
		}finally {
			DBConnection.seDeconnecter(cnx, pstmt);
		}
				
		return utilisateur;
	}
	
	
	/**
	 * Methode pour inserer un utilisateur
	 * @param une instance d'utilisateur
	 * @finally libere les connexions ouvertes
	 */
	@Override
	public void inserer(Utilisateur utilisateur) throws DALException, SQLException {
		
		Connection cnx = null;
		PreparedStatement pstmt = null;
		
		cnx = DBConnection.seConnecter();
		cnx.setAutoCommit(false);
		
		try {
			
			pstmt = cnx.prepareStatement(INSERER, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMotDePasse());
			pstmt.setInt(10, utilisateur.getCredit());
			pstmt.setBoolean(11, utilisateur.isAdministrateur());
			
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				int noUtilisateur = rs.getInt(1);
				utilisateur.setNoUtilisateur(noUtilisateur);
			}
						
		} catch (SQLException e) {
			cnx.rollback();
			throw new SQLException("Probleme methode inserer - "+e);
		}finally {
			cnx.setAutoCommit(true);
			DBConnection.seDeconnecter(cnx, pstmt);
		}
		
		
		
		
	}
	/**
	 * Methode pour supprimer un utilisateur
	 * @param une instance d'utilisateur
	 * @finally libere les connexions ouvertes
	 */
	
	@Override
	public void supprimer(Utilisateur utilisateur) throws DALException, SQLException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		
		cnx = DBConnection.seConnecter();
		cnx.setAutoCommit(false);
		
		try {
			pstmt = cnx.prepareStatement(SUPPRIMER);
			pstmt.setString(1, utilisateur.getPseudo());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			cnx.rollback();
			throw new DALException("erreur suppression utilisateur");
		}finally {
			cnx.setAutoCommit(true);
			DBConnection.seDeconnecter(cnx, pstmt);
		}
		
		
		
		
	}
	
	/**
	 * Methode pour modifier un utilisateur
	 * @param une instance d'utilisateur
	 * @finally libere les connexions ouvertes
	 */
	@Override
	public void modifier(Utilisateur utilisateur) throws DALException, SQLException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		
		cnx = DBConnection.seConnecter();
		cnx.setAutoCommit(false);
		
		try {
			pstmt = cnx.prepareStatement(MODIFIER);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMotDePasse());
			pstmt.setInt(10, utilisateur.getNoUtilisateur());
			System.out.println("int ok");
			pstmt.executeUpdate();
			System.out.println("update successfull");
		} catch (SQLException e) {
			e.printStackTrace();
			cnx.rollback();
			throw new DALException("erreur modification utilisateur");
			
			
		} finally {
			cnx.setAutoCommit(true);
			DBConnection.seDeconnecter(cnx, pstmt);
		}
		
	}

	/**
	 * M�thode v�rifiant l'existance d'une adresse mail en BDD
	 * @param mail
	 * @return
	 * @throws DALException
	 * @throws SQLException
	 */
	public boolean verifMailUnique(String mail) throws DALException, SQLException{
		Connection cnx = null;
		PreparedStatement pstmt = null;
		boolean unique = false;
		ResultSet rs = null;
		cnx = DBConnection.seConnecter();
		
		try {
			pstmt = cnx.prepareStatement("SELECT * FROM UTILISATEURS WHERE email = ? ;");
			pstmt.setString(1, mail);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				unique = true;
				
			}
		} catch (SQLException e) {
			throw new DALException("erreur verification mail unique");
		}finally {
			DBConnection.seDeconnecter(cnx, pstmt);
		}
		
		
		
		return unique;
	}
	
	/**
	 * M�thode v�rifiant l'existance d'un pseudo en BDD
	 * @param pseudo
	 * @return
	 * @throws DALException
	 * @throws SQLException
	 */
	public boolean verifPseudoUnique(String pseudo) throws DALException, SQLException{
		Connection cnx = null;
		PreparedStatement pstmt = null;
		boolean unique = false;
		ResultSet rs = null;
		cnx = DBConnection.seConnecter();
		
		try {
			pstmt = cnx.prepareStatement("SELECT * FROM UTILISATEURS WHERE pseudo = ? ;");
			pstmt.setString(1, pseudo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				unique = true;
				
			}
		} catch (SQLException e) {
			throw new DALException("erreur verification pseudo unique");
		}finally {
			DBConnection.seDeconnecter(cnx, pstmt);
		}
		return unique;
	
	}
	
	@Override
	public Utilisateur getUtilisateurPseudoMdp(String pseudo, String motDePasse) throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Utilisateur utilisateur = null;
		
		cnx = DBConnection.seConnecter();
		try {
			pstmt = cnx.prepareStatement(SELECTPSEUDOANDMDP);
			pstmt.setString(1, pseudo);
			pstmt.setString(2, motDePasse);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				utilisateur = new Utilisateur(rs.getString("pseudo"), rs.getString("mot_de_passe"));
			
			}
		} catch (SQLException e) {
			throw new DALException("echec de rechercheProfilAvecMotDePasse");
		}finally {
			DBConnection.seDeconnecter(cnx, pstmt);
		}
		return utilisateur;
				
	}

}
