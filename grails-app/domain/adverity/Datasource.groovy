package adverity

class Datasource {
    int id
    String value

    static mapping = {
        table "adv_schema.analytics_datasource"
        version false
    }
    static constraints = {
    }
    String toString(){
        return value
    }
}
