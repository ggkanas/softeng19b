package gr.ntua.ece.softeng19b.api;

import gr.ntua.ece.softeng19b.api.resource.*;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class RestfulApp extends Application {

    @Override
    public synchronized Restlet createInboundRoot() {

        Router router = new Router(getContext());

        //Perform a heath check
        router.attach("/HealthCheck", HealthCheck.class);
        //Init a new (empty) database with the default Admin user (username: admin, password: 321nimda)
        router.attach("/Reset", Reset.class);

        //Authenticate the user
        router.attach("/Login", Login.class);

        //You should make these REST endpoints available to Admin users only (Users with the Admin role)
        router.attach("/Admin/users", AddUser.class);
        router.attach("/Admin/users/{username}", UserManager.class);
        router.attach("/Admin/ActualTotalLoad", ImportActualTotalLoadDataSet.class);
        router.attach("/Admin/AggregatedGenerationPerType", ImportAggregatedGenerationPerTypeDataSet.class);
        router.attach("/Admin/DayAheadTotalLoadForecast", ImportDayAheadTotalLoadForecastDataSet.class);


        //Add more datasets

        //You should make these REST endpoints available to all authenticated users
        router.attach("/Logout", Logout.class);
        /*
         All routes accept a `format` query param (optional, either json or csv, default is json)
         All year, month and date attributes in routes are in the ISO-8601 format
           (YYYY, YYYY-MM, YYYY-MM-DD respectively)
         */
        router.attach(
            "/ActualTotalLoad/{AreaName}/{Resolution}/date/{date}",
            ActualTotalLoadForSpecificDate.class
        );
        //Add more datasets
        router.attach(
            "/ActualTotalLoad/{AreaName}/{Resolution}/month/{month}",
            ActualTotalLoadForSpecificMonth.class
        );
        router.attach(
            "/ActualTotalLoad/{AreaName}/{Resolution}/year/{year}",
            ActualTotalLoadForSpecificYear.class
        );


        //ProductionType can be AllTypes as well
        router.attach(
            "/AggregatedGenerationPerType/{AreaName}/{ProductionType}/{Resolution}/date/{date}",
            AggregatedGenerationPerTypeForSpecificDate.class
        );
        router.attach(
            "/AggregatedGenerationPerType/{AreaName}/{ProductionType}/{Resolution}/month/{month}",
            AggregatedGenerationPerTypeForSpecificMonth.class
        );
        router.attach(
            "/AggregatedGenerationPerType/{AreaName}/{ProductionType}/{Resolution}/year/{year}",
            AggregatedGenerationPerTypeForSpecificYear.class
        );

        router.attach(
            "/DayAheadTotalLoadForecast/{AreaName}/{Resolution}/date/{date}",
            DayAheadTotalLoadForecastForSpecificDate.class
        );

        router.attach(
            "/DayAheadTotalLoadForecast/{AreaName}/{Resolution}/month/{month}",
            DayAheadTotalLoadForecastForSpecificMonth.class
        );
        router.attach(
            "/DayAheadTotalLoadForecast/{AreaName}/{Resolution}/year/{year}",
            DayAheadTotalLoadForecastForSpecificYear.class
        );

        return router;
    }

}