package adverity

class Campaign {
    int id
    String value

    static mapping = {
        table "adv_schema.analytics_campaign"
        version false
    }

    static constraints = {
    }
    String toString(){
        return value
    }
}
