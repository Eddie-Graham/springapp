<div id="filter">
    <div class="pure-menu pure-menu-horizontal">
        Sort by: &nbsp;
        <ul class="pure-menu-list">
            <li class="pure-menu-item pure-menu-has-children pure-menu-allow-hover">
                <a id="menuLink1" class="pure-button pure-menu-link">Timestamp</a>

                <ul class="pure-menu-children">

                    <li class="pure-menu-item">
                        <button class="pure-button dropdownBtns"
                                onclick="refreshPostsByTimestamp(${myProfileUser.id})">Timestamp
                        </button>
                    </li>
                    <li class="pure-menu-item">
                        <button class="pure-button dropdownBtns"
                                onclick="refreshPostsByTotal(${myProfileUser.id})">Total
                        </button>
                    </li>
                    <li class="pure-menu-item">
                        <button class="pure-button dropdownBtns"
                                onclick="refreshPostsByLikes(${myProfileUser.id})">Likes
                        </button>
                    </li>
                    <li class="pure-menu-item">
                        <button class="pure-button dropdownBtns"
                                onclick="refreshPostsByDislikes(${myProfileUser.id})">Dislikes
                        </button>
                    </li>
                    <li class="pure-menu-item">
                        <button class="pure-button dropdownBtns"
                                onclick="refreshPostsByNoOfReplies(${myProfileUser.id})">Most Replied
                        </button>
                    </li>

                </ul>
            </li>
        </ul>
    </div>
</div>