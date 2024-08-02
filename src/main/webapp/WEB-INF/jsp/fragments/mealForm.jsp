<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--<section>--%>
<%--    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>--%>
<%--&lt;%&ndash;    `meal.new` cause javax.el.ELException - bug tomcat &ndash;%&gt;--%>
<%--    <h3><spring:message code="${meal.isNew() ? 'meal.add' : 'meal.edit'}"/></h3>--%>
<%--    <hr>--%>
<%--    <form method="post" action="meals">--%>
<%--        <input type="hidden" name="id" value="${meal.id}">--%>
<%--        <dl>--%>
<%--            <dt><spring:message code="meal.dateTime"/>:</dt>--%>
<%--            <dd><input type="datetime-local" value="${meal.dateTime}" name="dateTime" required></dd>--%>
<%--        </dl>--%>
<%--        <dl>--%>
<%--            <dt><spring:message code="meal.description"/>:</dt>--%>
<%--            <dd><input type="text" value="${meal.description}" size=40 name="description" required></dd>--%>
<%--        </dl>--%>
<%--        <dl>--%>
<%--            <dt><spring:message code="meal.calories"/>:</dt>--%>
<%--            <dd><input type="number" value="${meal.calories}" name="calories" required></dd>--%>
<%--        </dl>--%>
<%--        <button type="submit"><spring:message code="common.save"/></button>--%>
<%--        <button onclick="window.history.back()" type="button"><spring:message code="common.cancel"/></button>--%>
<%--    </form>--%>
<%--</section>--%>

<div class="modal fade" tabindex="-1" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><spring:message code="meal.add"/></h4>
                <button type="button" class="close" data-dismiss="modal" onclick="closeNoty()">&times;</button>
            </div>
            <div class="modal-body">
                <form id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="dateTime" class="col-form-label"><spring:message code="meal.dateTime"/></label>
                        <input type="datetime-local" class="form-control" id="dateTime" name="dateTime"
                               placeholder="<spring:message code="user.name"/>">
                    </div>

                    <div class="form-group">
                        <label for="description" class="col-form-label"><spring:message code="meal.description"/></label>
                        <input type="text" class="form-control" id="description" name="description"
                               placeholder="<spring:message code="meal.description"/>">
                    </div>

                    <div class="form-group">
                        <label for="calories" class="col-form-label"><spring:message code="meal.calories"/></label>
                        <input type="number" class="form-control" id="calories" name="calories"
                               placeholder="<spring:message code="meal.calories"/>">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="closeNoty()">
                    <span class="fa fa-close"></span>
                    <spring:message code="common.cancel"/>
                </button>
                <button type="button" class="btn btn-primary" onclick="save()">
                    <span class="fa fa-check"></span>
                    <spring:message code="common.save"/>
                </button>
            </div>
        </div>
    </div>
</div>
