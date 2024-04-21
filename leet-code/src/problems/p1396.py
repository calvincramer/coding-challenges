class UndergroundSystem:
    def __init__(self):
        # map from start station name to map of end station name to tuple of total trips and total time
        self._station_stats = dict()
        # map from customer id to tuple of station name and start time
        self._curr_trips = dict()

    def checkIn(self, id: int, stationName: str, t: int) -> None:
        if id in self._curr_trips.keys():
            raise ValueError("Customer checking in twice, not expected")
        self._curr_trips[id] = (stationName, t)

    def checkOut(self, id: int, stationName: str, t: int) -> None:
        if id not in self._curr_trips.keys():
            raise ValueError(f"Customer {id} did not check in before checking out")
        start_station, start_time = self._curr_trips[id]
        # Remove from tracking
        del self._curr_trips[id]
        # Add to stats
        if start_station not in self._station_stats.keys():
            self._station_stats[start_station] = dict()
        if stationName not in self._station_stats[start_station].keys():
            self._station_stats[start_station][stationName] = (0, 0)
        trips, time = self._station_stats[start_station][stationName]
        self._station_stats[start_station][stationName] = (trips+1, time + t - start_time)

    def getAverageTime(self, startStation: str, endStation: str) -> float:
        if startStation not in self._station_stats.keys():
            raise ValueError()
        if endStation not in self._station_stats[startStation]:
            raise ValueError()
        trips, time = self._station_stats[startStation][endStation]
        return time * 1.0 / trips


obj = UndergroundSystem()
obj.checkIn('cust', 'stationA', 10)
obj.checkOut('cust', 'stationB', 20)
print(obj.getAverageTime('stationA', 'stationB'), 'expected 10')
