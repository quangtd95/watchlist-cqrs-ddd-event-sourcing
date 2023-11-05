package com.qstudio.investing.watchlist_query.adapter.inbound.api.rest.response

import com.qstudio.investing.watchlist_query.core.model.StockView

data class GetStocksResponse(val stocks: List<StockView>)