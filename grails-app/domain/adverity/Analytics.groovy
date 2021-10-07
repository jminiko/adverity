package adverity

class Analytics {
    int id
    Date daily
    int clicks
    int impressions
    Datasource datasource
    Campaign campaign
    static mapping = {
        table "adv_schema.analytics_main"
        version false
    }
    static constraints = {
    }
    String toString(){
        return " datasource: "+  datasource.value + " , campaign: "+campaign.value + " , daily: "+ daily + " "
    }
}
