from datetime import datetime, timedelta

print(datetime.strftime(datetime.now() + timedelta(hours=9), "%Y-%m-%d"))