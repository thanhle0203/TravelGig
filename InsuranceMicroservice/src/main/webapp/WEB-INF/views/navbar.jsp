<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="#">
        <img src="/images/AFI.png" alt="AFI Insurance" height="50" alt="Logo">
    </a>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="#">Blog Insurance</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Contact AFI</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Manage Insurance</a>
            </li>
            <li class="nav-item">
                <% if (request.getUserPrincipal() == null) { %>
                    <a class="nav-link" href="/login">Sign In</a>
                <% } else { %>
                    <a class="nav-link" href="/logout">Log Out</a>
                <% } %>
            </li>
        </ul>
    </div>
</nav>