<#import "parts/common.ftl" as ht>
<#import "parts/login.ftl" as l>

<@ht.page>
<div>

    <@l.logout />
     <span><a href="/user">User list</a></span>
</div>
<div>
    <form action="add" method="post" enctype="multipart/form-data">
        <input type="text" name="name" placeholder="Введите имя работника" />
        <input type="text" name="email" placeholder="Введите Email"/>
        <input type="file" name="file"/>
        <button type="submit">Добавить</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
    </form>
</div>

<form method="get" action="add">
    <input type="text" name="filterName" placeholder="Поиск по имени" value=${filterName}>
    <button type="submit">Найти</button>
</form>



<div>Пользователи</div>
<#list workers as worker>
<div>
<ul>
<li>
${worker.id}
${worker.name}
${worker.email}
${worker.usrName}
    <div>
        <#if worker.filename??>
            <img src = "/img/${worker.filename}" />
        </#if>
    </div>
    <form action="delete" method="post" >
            <input type="hidden" name="idworker" value=${worker.id} />
            <button type="submit">Удалить</button>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
    </form>
</li>
</ul>
</div>
<#else>
No message
</#list>
<div>
${error}
</div>

</@ht.page>