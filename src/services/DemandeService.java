package services;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connexion.Connexion;
import dao.IDao;
import entities.Demande;
import entities.Fournisseur;
import entities.LigneCommande;

public class DemandeService implements IDao<Demande> {
    LigneDemandeService ligneDemandeService = new LigneDemandeService();
    private FournisseurService cs;

    public DemandeService() {
        cs = new FournisseurService();
    }

    @Override
    public boolean create(Demande o) {
        try {
            String req = "insert into demande values (null, ?, ? )";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, new Date(o.getDate().getTime()));
            ps.setInt(2, o.getFournisseur().getId());

            if (ps.executeUpdate() == 1) {
            
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Demande o) {
        try {
            String req = "delete from demande where id  = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, o.getId());
            if (ps.executeUpdate() == 1)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Demande o) {
        try {
            String req = "update demande set date = ?, fournissueur = ? where id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setDate(1, new Date(o.getDate().getTime()));
            ps.setInt(2, o.getFournisseur().getId());
            ps.setInt(3, o.getId());
            if (ps.executeUpdate() == 1)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Demande findById(int id) {
        Demande demande= null;
        try {
            String req = "select * from demande where id=" + id;
            Statement st = Connexion.getConnection().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next())
               demande = new Demande(rs.getInt("id"),
                        rs.getDate("date"), cs.findById(rs.getInt("fournissueur")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return demande;

    }
public Demande findByFournisseur(Fournisseur fournisseur) {
        Demande demande = null;
        try {
            String req = "select * from demande where fournissueur=" + fournisseur.getId();
            Statement st = Connexion.getConnection().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next())
               demande = new Demande(rs.getInt("id"),
                        rs.getDate("date"), cs.findById(rs.getInt("fournissueur")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return demande;

    }
    @Override
    public List<Demande> findAll() {
        List<Demande> demandes = new ArrayList<Demande>();
        try {
            String sql = "select * from demande";
            Statement st = Connexion.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
                demandes.add(new Demande(rs.getInt("id"),
                        rs.getDate("date"), cs.findById(rs.getInt("fournissueur"))));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return demandes;
    }

  

}
