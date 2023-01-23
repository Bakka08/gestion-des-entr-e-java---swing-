package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connexion.Connexion;
import dao.IDao;
import entities.Sortie;
import java.sql.Date;

public class SortieService implements IDao<Sortie> {
CommandeService cms;
    public SortieService() {
        cms=new CommandeService();
    }

    @Override
    public boolean create(Sortie o) {
        try {
            String req = "insert into sortie values (null, ?)";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, o.getCommande().getId());
           
            if (ps.executeUpdate() == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                int lastInserted = rs.getInt(1);
                o.setId(lastInserted);
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Sortie o) {
        try {
            String req = "delete from sortie where id  = ?";
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
    public boolean update(Sortie o) {
        try {
            String req = "update sortie set commande= ? where id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, o.getId());
            ps.setInt(2, o.getCommande().getId());
            
            if (ps.executeUpdate() == 1)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Sortie findById(int id) {
        Sortie sortie = null;
        try {
            String sql = "select * from sortie where id=" + id;
            Statement st = Connexion.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
                sortie = new Sortie(rs.getInt("id"),cms.findById(rs.getInt("commande")) );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sortie;
    }

    @Override
    public List<Sortie> findAll() {
        List<Sortie> sorties = new ArrayList<Sortie>();
      try {
            String sql = "select * from sortie ";
            Statement st = Connexion.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
                sorties.add( new Sortie(rs.getInt("id"),cms.findById(rs.getInt("commande")))) ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sorties;

}


}
