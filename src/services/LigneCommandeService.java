package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connexion.Connexion;
import dao.IDao;
import entities.Client;
import entities.Commande;
import entities.Produit;
import entities.LigneCommande;
import java.sql.Date;

public class LigneCommandeService implements IDao<LigneCommande> {
private CommandeService cms;
private ProduitService ps;
    @Override
    public boolean create(LigneCommande o) {
        try {
            String req = "insert into lignecommande values (?, ?, ? , ? )";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, o.getQuantite());
            ps.setDouble(2, o.getPrix());
            ps.setInt(3, o.getCommand().getId());
            ps.setInt(4, o.getProduit().getId());
            if (ps.executeUpdate() == 1)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(LigneCommande o) {
        try {
            String req = "delete from lignecommande where commande = ? and produit = ?  and quantite = ? ";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, o.getCommand().getId());
            ps.setInt(2, o.getProduit().getId());
            ps.setInt(3, o.getQuantite());
            if (ps.executeUpdate() == 1)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(LigneCommande o) {
        try {
            String req = "update lignecommande set quantite = ?, prix = ? , commande = ?, produit = ? where commande = " +o.getCommand().getId();
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, o.getQuantite());
            ps.setDouble(2, o.getPrix());
            ps.setInt(3, o.getCommand().getId());
            ps.setInt(4, o.getProduit().getId());
            if (ps.executeUpdate() == 1)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override


   
    public List<LigneCommande> findAll() {
       CommandeService cs = new CommandeService();
       ProduitService ps = new ProduitService();
        List<LigneCommande> ligneCommandes = new ArrayList<LigneCommande>();
        try {
            String sql = "select * from lignecommande";
            Statement st = Connexion.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
                ligneCommandes.add(new LigneCommande(rs.getInt("quantite"), rs.getDouble("prix"), cs.findById(rs.getInt("commande")), ps.findById(rs.getInt("produit"))));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ligneCommandes;
    }

   
  public LigneCommande findByCommande(Commande  commande ) {
          LigneCommande ligneCommande  = null;
        try {
            String sql = "select * from lignecommande where commande=" + commande.getId();
            Statement st = Connexion.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
                ligneCommande = new LigneCommande(rs.getInt("quantite"), rs.getDouble("prix"), commande, ps.findById(rs.getInt("produit")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ligneCommande;
    }

    @Override
    public LigneCommande findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
