<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>

<@c.page><

<h1>${message}</h1>
<br>
<nav>
    <div class="nav">
        <br>
        <#list menu as menuItem>
            <a href="${menuItem.link}">${menuItem.label}</a>
        </#list>
    </div>
</nav>

</@c.page>
