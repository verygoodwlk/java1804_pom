local id = KEYS[1]

local number = ARGV[1]

local order = ARGV[2]

local save = redis.call("get", "kill"..id)

if save == nil or save < number then
	return 1
end

save = save - number
redis.call("set", "kill"..id, save)
redis.call("rpush", "order"..id, order)

if save > 0 then
  return 0
else
  return 2
end