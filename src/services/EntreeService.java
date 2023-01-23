package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connexion.Connexion;
import dao.IDao;
import entities.entree;
import java.sql.Date;

public class EntreeService implements IDao<entree> {
DemandeService cms;
    public EntreeService() {
        cms=new DemandeService();
    }

    @Override
    public boolean create(entree o) {
        try {
            String req = "insert into entree values (null, ?)";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, o.getDemande().getId());
           
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
    public boolean delete(entree o) {
        try {
            String req = "delete from entree where id  = ?";
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
    public boolean update(entree o) {
        try {
            String req = "update entree set demande= ? where id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, o.getId());
            ps.setInt(2, o.getDemande().getId());
            
            if (ps.executeUpdate() == 1)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public entree findById(int id) {
        entree entree1 = null;
        try {
            String sql = "select * from entree where id=" + id;
            Statement st = Connexion.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
                entree1 = new entree(rs.getInt("id"),cms.findById(rs.getInt("demande")) );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entree1;
    }

    @Override
    public List<entree> findAll() {
        List<entree> sorties = new ArrayList<entree>();
      try {
            String sql = "select * from entree ";
            Statement st = Connexion.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
                sorties.add( new entree(rs.getInt("id"),cms.findById(rs.getInt("demande")))) ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sorties;

}


}
