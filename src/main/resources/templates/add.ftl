<#import "parts/common.ftl" as ht>
<#import "parts/login.ftl" as l>

<@ht.page>
<div>

    <@l.logout />
     <span><a href="/user">User list</a></span>
</div>
<div>
    <form action="add" method="post">
        <input type="text" name="name" placeholder="Введите имя работника" />
        <input type="text" name="email" placeholder="Введите Email">
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