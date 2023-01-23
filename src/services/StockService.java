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
import entities.stock;
import java.sql.Date;

public class StockService implements IDao<stock> {

    private ProduitService ps;

    public StockService() {
        ps = new ProduitService();
    }

    @Override
    public boolean create(stock o) {
            try {
            String req = "insert into stock values (?, ? )";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, o.getProduit().getId());
            ps.setInt(2, o.getQuantite());
            if (ps.executeUpdate() == 1) {
            
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(stock o) {
          try {
            String req = "delete from stock where produit  = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, o.getProduit().getId());
            if (ps.executeUpdate() == 1)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(stock o) {
          try {
            String req = "update stock set  quantite = ? where produit = " +o.getProduit().getId();
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
                  
            ps.setInt(1, o.getQuantite());
            if (ps.executeUpdate() == 1)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public stock findById(int id) {
         stock stock1 = null;
        try {
            String req = "select * from stock where produit=" + id;
            Statement st = Connexion.getConnection().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next())
                stock1=new stock(ps.findById(rs.getInt("produit")), rs.getInt("quantite"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stock1;

    }
       public stock findByProduit(Produit produit) {
         stock stock1 = null;
        try {
            String req = "select * from stock where produit=" + produit.getId();
            Statement st = Connexion.getConnection().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next())
                stock1=new stock(ps.findById(rs.getInt("produit")), rs.getInt("quantite"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stock1;

    }
   

    @Override
    public List<stock> findAll() {
   List<stock> stocks = new ArrayList<stock>();
        try {
            String sql = "select * from stock";
            Statement st = Connexion.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
                stocks.add(new stock(ps.findById(rs.getInt("produit")), rs.getInt("quantite")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stocks;    }

   
}
