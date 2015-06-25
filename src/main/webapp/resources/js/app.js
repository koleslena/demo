/**
 * Created by elenko on 23.06.15.
 */
var Item = React.createClass({
    render: function () {
        return (
            <div className="item">
            <div>Hello {this.props.name} ! Your id is {this.props.id}. Your content is {this.props.content}. Published {this.props.published ? 1 : 0}</div>
        </div>
        );
    }
});

var NavigateLink = React.createClass({
    render: function () {
        return (
            <button className="navigateLink" onClick={this.props.onClick}>{this.props.name}</button>
        );
    }
});

var PagePanel = React.createClass({
    render: function () {
        return (
            <div className="pagePanel">Page {this.props.pageNumber + 1}/ TotalPages {this.props.totalPages}</div>
        );
    }
});

var ItemPagingList = React.createClass({
    getInitialState: function () {
        var selfLink = "http://localhost:8080/demo/api/public/items/";
        return {data: {content: [], selfLink: selfLink, nextLink: null, prevLink: null, paging: {pageNumber: 0, totalPages: 1}}};
    },

    loadData: function (url) {
        $.ajax({
            url: url,
            dataType: 'json',
            success: function (data) {
                var selfLink, nextLink, prevLink;
                data.links.forEach(function (item) {
                    switch (item.rel){
                        case "self": selfLink = item.href; break;
                        case "next": nextLink = item.href; break;
                        case "prev": prevLink = item.href; break;
                    }
                });

                this.setState({
                    data: {
                        content: data.content,
                        selfLink: selfLink,
                        nextLink: nextLink,
                        prevLink: prevLink,
                        paging: {
                            pageNumber: data.page.number,
                            totalPages: data.page.totalPages
                        }
                    }
                });
            }.bind(this),
            error: function (xhr, status, err) {
                //console.error(this.props.url, status, err.toString());
            }.bind(this)
        });
    },

    nextData: function () {
        this.loadData(this.state.data.nextLink);
    },

    prevData: function () {
        this.loadData(this.state.data.prevLink);
    },

    componentDidMount: function () {
        this.loadData(this.state.data.selfLink);
    },

    render: function () {
        var itemNodes = this.state.data.content.map(function (item) {
            return (
                <Item name={item.name} id={item.id} content={item.content} published={item.published}></Item>
            );
        });
        var nextLink = this.state.data.nextLink ? (<NavigateLink name="next" onClick={this.nextData}></NavigateLink>) : '';
        var prevLink = this.state.data.prevLink ? (<NavigateLink name="prev" onClick={this.prevData}></NavigateLink>) : '';
        return (
            <div className="itemPagingList">
                {itemNodes}
                <PagePanel pageNumber={this.state.data.paging.pageNumber} totalPages={this.state.data.paging.totalPages}></PagePanel>
                {prevLink}
                {nextLink}
            </div>
        );
    }
});

var ItemList = React.createClass({
        getInitialState: function () {
            return {data: []};
        },

        componentDidMount: function () {
            $.ajax({
                url: "http://localhost:8080/demo/api/public/items/",
                dataType: 'json',
                success: function (data) {
                    this.setState({data: data.content});
                }.bind(this),
                error: function (xhr, status, err) {
                    //console.error(this.props.url, status, err.toString());
                }.bind(this)
            });
        },

        render: function () {
                var itemNodes = this.state.data.map(function (item) {
                                return (
                                    <Item name={item.name} id={item.id} content={item.content} published={item.published}></Item>
                                );
                            });
            return (
                <div className="itemList">
                {itemNodes}
                </div>
            );
        }
});

//var renderApp = function () {
    /*React.render(
        <ItemList/>,
        document.getElementById('content')
    );*/
      React.render(
        <ItemPagingList/>,
        document.getElementById('content')
    );

//};
