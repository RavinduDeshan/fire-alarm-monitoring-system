const { version } = require("chai");
const semver = require("semver");
const service = require("../service");

class serviceRegistry {
  constructor(log) {
    this.log = log;
    this.services = {};
    this.timeout = 30;
  }

  get(name, version) {
    // cleanup

    this.cleanup();

    const candidates = Object.values(this.services).filter(
      (service) =>
        service.name === name && semver.satisfies(service.version, version)
    );

    return candidates[Math.floor(Math.random() * candidates.length)];
  }

  register(name, version, ip, port) {
    // cleanup

    this.cleanup();

    const key = name + version + ip + port;

    this.log.debug(
      `Service received ${name}, version ${version}, at ${ip}:${port}`
    );

    if (!this.services[key]) {
      this.services[key] = {};
      this.services[key].time = Math.floor(new Date() / 1000);
      this.services[key].ip = ip;
      this.services[key].port = port;
      this.services[key].name = name;
      this.services[key].version = version;
      this.log.debug(
        `Service registered ${name}, version ${version}, at ${ip}:${port}`
      );

      return key;
    }
    this.services[key].timestamp = Math.floor(new Date() / 1000);
    this.log.debug(
      `Service Updated ${name}, version ${version}, at ${ip}:${port}`
    );

    return key;
  }

  unregister(name, version, ip, port) {
    const key = name + version + ip + port;
    delete this.services[key];
    this.log.debug(
      `Service Deleted ${name}, version ${version}, at ${ip}:${port}`
    );

    return key;
  }

  cleanup() {
    const now = Math.floor(new Date() / 1000);
    Object.keys(this.services).forEach((key) => {
      if (this.services[key].timestamp + this.timeout < now) {
        delete this.services[key];
        this.log.debug(`Service Removed due to time out ${key}`);
      }
    });
  }
}
module.exports = serviceRegistry;
