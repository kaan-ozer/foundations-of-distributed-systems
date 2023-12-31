package de.fhws.fiw.fds.exam03.server.api.states.events;

import de.fhws.fiw.fds.exam03.server.api.models.Event;
import de.fhws.fiw.fds.exam03.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionState;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

import javax.ws.rs.core.GenericEntity;
import java.util.Collection;

public class GetAllEvents extends AbstractGetCollectionState<Event> {

    public GetAllEvents( final GetAllEvents.Builder builder )
    {
        super( builder );
    }



    public static class Builder extends AbstractGetCollectionStateBuilder<Event>
    {
        @Override public AbstractState build( )
        {
            return new GetAllEvents( this );
        }
    }

    @Override protected void defineTransitionLinks( )
    {

        addLink( EventUri.REL_PATH, EventRelTypes.CREATE_EVENT, getAcceptRequestHeader( ) );
        addLink(EventUri.REL_PATH_BY_SEARCH,EventRelTypes.GET_ALL_EVENTS_BY_SEARCH,getAcceptRequestHeader());
        addLink(EventUri.REL_PATH_BY_DATE,EventRelTypes.GET_ALL_EVENTS_BY_DATE,getAcceptRequestHeader());
        addLink(EventUri.REL_PATH_BY_SEARCH_WITH_ORDER,EventRelTypes.GET_ALL_EVENTS_BY_SEARCH_WITH_ORDER,getAcceptRequestHeader());
        addLink(EventUri.REL_PATH_BY_DATE_WITH_ORDER,EventRelTypes.GET_ALL_EVENTS_BY_DATE_WITH_ORDER,getAcceptRequestHeader());
    }



    protected void defineHttpResponseBody( )
    {

        this.responseBuilder.entity( new GenericEntity<Collection<Event>>( this.result.getResult( ) )
        {
        } );
    }


    public static class AllEvents extends AbstractQuery<Event> {
        @Override
        protected CollectionModelResult<Event> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException {
            return DaoFactory.getInstance().getEventDao().readAll();
        }
    }

    @Override
    protected void authorizeRequest() {

    }


}
