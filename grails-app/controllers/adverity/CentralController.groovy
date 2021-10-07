package adverity


import grails.rest.*
import grails.converters.*

class CentralController extends RestfulController {
    static responseFormats = ['json', 'xml', 'html']
    static allowedMethods = ["GET"]

    def CentralController() {
        super(Analytics)
    }

    def index() {
        /**
         * parameters for central query and returning a json or xml or whatever in the future
         * selectedCols params is a string consisting of either in combination separated by comma (","):
         * <code>id,datasource,campaign,clicks,impressions</code>
         */
        String selectedCols = params.selectedCols ? params.selectedCols : ""
        selectedCols = selectedCols?.replace("campaign", "al.campaign.value")
        selectedCols = selectedCols?.replace("datasource", "al.datasource.value")


        /**
         * the groupBy parameter is relevant only if there is an aggregated function computed (avg, min, max count or sum)
         * there are only 3 possibilities in combination (permutation): campaign/datasource
         */
        String groupBy = params.groupBy ? params.groupBy : ""
        groupBy = groupBy?.replace("campaign", "al.campaign.value")
        groupBy = groupBy?.replace("datasource", "al.datasource.value")
        if (groupBy) {
            groupBy = " group by " + groupBy
        } else {
            groupBy = ""
        }
        /**
         * the maxCols is an aggregated function called on clicks and impressions
         */
        String maxCols = params.maxCols ? params.maxCols : ""
        if (maxCols) {
            maxCols = maxCols.replace("clicks", " max(al.clicks)")
            maxCols = maxCols.replace("daily", " max(al.daily)")
            maxCols = maxCols.replace("impressions", "max(al.impressions)")
            maxCols += ","
        } else {
            maxCols = ""
        }
        /**
         * the count is an aggregated function called on clicks and impressions
         */
        String count = params.count ? params.count : ""
        if (count) {
            count = count.replace("clicks", " count(al.clicks)")
            count = count.replace("impressions", "count(al.impressions)")
            count = count.replace("daily", " count(al.daily)")
            count += ","
        } else {
            count = ""
        }

        String minCols = params.minCols
        if (minCols) {
            minCols = minCols.replace("clicks", " min(al.clicks)")
            minCols = minCols.replace("impressions", "min(al.impressions)")
            minCols = minCols.replace("daily", " min(al.daily)")
            minCols += ","
        } else {
            minCols = ""
        }
        String avgCols = params.avgCols
        if (avgCols) {
            avgCols = avgCols.replace("clicks", " abg(al.clicks)")
            avgCols = avgCols.replace("impressions", "avg(al.impressions)")
            avgCols = avgCols.replace("daily", " avg(al.daily)")
            avgCols += ","
        } else {
            avgCols = ""
        }
        String sumCols = params.sumCols
        if (sumCols) {
            sumCols = sumCols.replace("clicks", " sum(al.clicks)")
            sumCols = sumCols.replace("impressions", "sum(al.impressions)")
            sumCols = sumCols.replace("daily", " sum(al.daily)")
            sumCols += ","
        } else {
            sumCols = ""
        }
        String dateWhereClause = params.dateWhereClause

        def colId = ""
        if (maxCols == "" && count == "" && selectedCols == "") {
            colId = " id "
        }
        String s1 = maxCols + minCols+avgCols+sumCols+ count + selectedCols + colId
        String[] columns = (s1).split(",")

        String query = "select " + s1+ " from Analytics al  " + groupBy
        def idx = 0
        def results = Analytics.executeQuery(query)
        render results

    }

}
