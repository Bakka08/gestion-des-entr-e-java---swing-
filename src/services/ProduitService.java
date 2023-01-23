package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connexion.Connexion;
import dao.IDao;
import entities.Commande;
import entities.Produit;

public class ProduitService implements IDao<Produit> {
    private List<Produit> produits;
    private ProduitService cs;
FournisseurService fs=new FournisseurService();
    public ProduitService() {
        this.produits = new ArrayList<Produit>();
        // cs = new ProduitService();
    }

    @Override
    public boolean create(Produit o) {
        try {
            String req = "insert into produit values (null, ?, ? ,?)";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setString(1, o.getDesignation());
            ps.setDouble(2, o.getPrixAchat());
            ps.setInt(3, o.getFournisseur().getId());
            if (ps.executeUpdate() == 1)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Produit o) {
        try {
            String req = "delete from produit where id  = ?";
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
    public boolean update(Produit o) {
        try {
            String req = "update produit set designation = ? , prixAchat = ?, fournisseur = ? where id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setString(1, o.getDesignation());
            ps.setDouble(2, o.getPrixAchat());
            ps.setInt(3, o.getFournisseur().getId());
            ps.setInt(4, o.getId());
            if (ps.executeUpdate() == 1)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    
    public Produit findById(int id) {
        Produit produit = null;
        try {
            String req = "select * from produit where id=" + id;
            Statement st = Connexion.getConnection().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next())
                produit = new Produit(rs.getInt("id"),rs.getString("designation"),rs.getDouble("prixachat"),
                         fs.findById(rs.getInt("fournisseur")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produit;

    }
     public Produit findBy(String designation) {
        Produit produit2 = null;
        try {
            String req = "select * from produit where designation=" + designation;
            Statement st = Connexion.getConnection().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next())
                produit2 = new Produit(rs.getInt("id"),designation,rs.getDouble("prixachat"),
                         fs.findById(rs.getInt("fournisseur")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produit2;

    }

    @Override
    public List<Produit> findAll() {
        List<Produit> produits = new ArrayList<Produit>();
        try {
            String sql = "select * from produit";
            Statement st = Connexion.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
                produits.add(new Produit(rs.getInt("id"),rs.getString("designation"),rs.getDouble("prixachat"),
                         fs.findById(rs.getInt("fournisseur"))));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produits;
    }


}
