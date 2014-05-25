/*
    RESPONSÁVEL POR GERENCIAR A CONEXÃO COM O BANCO DE DADOS ATRAVÉS DO HIBERNATE
 */

package br.com.semeru.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
    
    private static final SessionFactory SESSION_FACTORY;
    public static final String HIBERNATE_SESSION = "hibernate_session"; // armazena a sessão do Hibernate
    
    static {
        try {
            System.out.println("Tentando abrir uma SESSION FACTORY...");
            
            Configuration configuration = new Configuration().configure();
            System.out.println("Configuration = OK...");
            
            ServiceRegistry serviceRegistryBuilder = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();    
            System.out.println("ServiceRegistry = OK...");
            
            SESSION_FACTORY = configuration.buildSessionFactory(serviceRegistryBuilder);
            System.out.println("SessionFactory = OK...");
            
            System.out.println("Sessão iniciada com sucesso!");
        } catch (HibernateException e) {
            System.out.println("Erro ao tentar iniciar a sessão:\n" + e);
            
            throw new ExceptionInInitializerError(e);
        } // try..catch
    } // staic

    public static SessionFactory getSESSION_FACTORY() {
        return SESSION_FACTORY;
    }
    
} // fim da classe