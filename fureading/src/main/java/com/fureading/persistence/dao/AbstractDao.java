package com.fureading.persistence.dao;import java.io.Serializable;import java.util.List;import org.apache.commons.lang3.StringUtils;import org.hibernate.Session;import org.hibernate.SessionFactory;import org.hibernate.criterion.Order;/** * Impl�mentation de l'interface IGenericDao<T, U>. * * Classe g�n�rique d'acc�s aux donn�es en base. Elle permet d'effectuer des op�ration simple : * ajouter, supprimer, mettre � jour, r�cup�rer un ou tous les �l�ments d'une table. * * @param <T> *          Type de la table correspondante * @param <U> *          Type de la cl� primaire de la table */public abstract class AbstractDao<T, U extends Serializable> implements IGenericDao < T, U > {  /** Le clazz. */  protected Class < T > clazz;  /** Le session factory. */  private SessionFactory sessionFactory;  /**   * Constructeur.   *   * @param clazz   *          la classe correspondante � la table   */  public AbstractDao(Class < T > clazz) {    this.clazz = clazz;  }  /*   * (non-Javadoc)   *    * @see fr.guichetentreprises.persistance.dao.IGenericDao#delete(java.lang.Object)   */  @Override  public void delete(T o) {    this.getSession().delete(o);  }  /*   * (non-Javadoc)   *    * @see fr.guichetentreprises.persistance.dao.IGenericDao#get(java.io.Serializable)   */  @Override  public T get(U uuid) {    T retour = null;    if (uuid != null) {      retour = (T) this.getSession().get(this.clazz, uuid);    }    return retour;  }  /*   * (non-Javadoc)   *    * @see fr.guichetentreprises.persistance.dao.IGenericDao#getAll(java.lang.String)   */  @Override  public List < T > getAll(String column) {    List < T > retour = null;    if (column != null) {      retour = this.getSession().createCriteria(this.clazz).addOrder(Order.asc(column)).list();    }    return retour;  }  /**   * getter.   *   * @return session   */  public Session getSession() {    return this.sessionFactory.getCurrentSession();  }  /*   * (non-Javadoc)   *    * @see fr.guichetentreprises.persistance.dao.IGenericDao#save(java.lang.Object)   */  @Override  @SuppressWarnings("unchecked")  public U save(T o) {    U retour = null;    if (o != null) {      retour = (U) this.getSession().save(o);    }    return retour;  }  /**   * Setter.   *   * @param sessionFactory   *          session   */  public void setSessionFactory(SessionFactory sessionFactory) {    this.sessionFactory = sessionFactory;  }  /*   * (non-Javadoc)   *    * @see fr.guichetentreprises.persistance.dao.IGenericDao#update(java.lang.Object)   */  @Override  public void update(T o) {    if (o != null) {      this.getSession().update(o);    }  }  /**   * Remplace accent par wild card accents.   *   * @param query   *          le query   * @return le string   */  public String remplaceAccentParWildCardAccents(String query) {    String retour = null;    if (StringUtils.isNotBlank(query)) {      String queryWithoutAccent = StringUtils.stripAccents(query);      char[] charArray = queryWithoutAccent.toCharArray();      char[] charArrayQuery = query.toCharArray();      for (int i = 0; i < charArray.length; i++) {        if (charArray[i] != charArrayQuery[i]) {          charArrayQuery[i] = '%';        }      }      retour = new String(charArrayQuery);    }    return retour;  }  /**   * Get le clazz.   *   * @return the clazz   */  public Class < T > getClazz() {    return this.clazz;  }  /*   * public void updateWithVersion(Map<String, Object> mapColumnData, Map<String, Object>   * mapWhereCondition) throws ConcurrentModificationException, IllegalStateException { List<Object>   * values = new ArrayList<Object>(); List<Type> types = new ArrayList<Type>(); StringBuilder   * strBuilder = new StringBuilder();   *    * strBuilder.append("update "); strBuilder.append(this.clazz.getSimpleName());   * strBuilder.append(" set ");   *    * addParametersToQuery(strBuilder, ",", mapColumnData, values, types);   *    * strBuilder.append(" where "); addParametersToQuery(strBuilder, " and ", mapWhereCondition,   * values, types);   *    * Type[] typesArray = types.toArray(new Type[types.size()]); Object[] valuesArray =   * values.toArray(new Object[values.size()]); Query query = getSession   * ().createQuery(strBuilder.toString()).setParameters(valuesArray, typesArray);   *    * int result = query.executeUpdate(); if (result == 0) { throw new   * ConcurrentModificationException ("Erreur lors de la modification de l' objet " +   * mapColumnData.get("id") +   * ", aucun �l�ment avec la m�me version et le m�me identifiant en base"); } if (result > 1) {   * throw new IllegalStateException("Erreur lors de la modification de l' objet " +   * mapColumnData.get("id") + ", il existe plusieurs �l�ment avec cette identifiant"); } }   *    * /** Ajoute les param�tres de la Map 'map' dans le StringBuilder 'strBuilder' en utilisant le   * separateur 'separator'. Ajoute �galement les valeurs dans la liste des bindings 'values' ainsi   * que les types des valeurs dans la liste 'types'.   *    * @param strBuilder   *    * @param separator   *    * @param map   *    * @param values   *    * @param types   */  /*   * private void addParametersToQuery(StringBuilder strBuilder, String separator, Map<String,   * Object> map, List<Object> values, List<Type> types) { String mapKey = null; boolean first =   * true;   *    * for (Map.Entry<String, Object> m : map.entrySet()) { // Ajoute une virgule en d�but de cha�ne   * uniquement si ce n'est pas // la premi�re valeur if (!first) { strBuilder.append(separator); }   * else { first = false; }   *    * mapKey = m.getKey(); strBuilder.append(mapKey); strBuilder.append(" = ?");   *    * values.add(m.getValue()); types.add(getType(m.getValue())); } }   *    * /** Permet de faire le mapping entre les types Java et les types Hibernate   *    * @param o   *    * @return   */  /*   * private Type getType(Object o) { if (o == null) { return StringType.INSTANCE; } if   * (o.getClass() == Date.class) { return DateType.INSTANCE; } else if (o.getClass() ==   * Integer.class) { return IntegerType.INSTANCE; } else { return StringType.INSTANCE; }   *    * }   */}